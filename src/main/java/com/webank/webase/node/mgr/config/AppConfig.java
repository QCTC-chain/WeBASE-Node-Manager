package com.webank.webase.node.mgr.config;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.nacos.api.config.ConfigChangeEvent;
import com.alibaba.nacos.api.config.ConfigChangeItem;
import com.alibaba.nacos.client.config.listener.impl.AbstractConfigChangeListener;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @author zhangyang
 * @version 1.0
 * @project WeBASE-Node-Manager
 * @description
 * @date 2023/9/25 16:40:46
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class AppConfig implements InitializingBean {

    private final NacosConfigManager nacosConfigManager;

    @Value("${host-mgr.ssh-key}")
    private String sshKey;


    @Override
    public void afterPropertiesSet() throws Exception {
        // 每次应用启动后，先写一次ssh-key文件
        writeSSHKey(sshKey);

        AbstractConfigChangeListener listener =
                new AbstractConfigChangeListener() {
                    @Override
                    public void receiveConfigChange(ConfigChangeEvent event) {
                        Collection<ConfigChangeItem> changeItems = event.getChangeItems();
                        log.info("receiveConfigChange:{}", changeItems.toString());
                    }

                    @Override
                    public void receiveConfigInfo(String configInfo) {
                        try {
                            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
                            Map<String, Object> map = mapper.readValue(configInfo, Map.class);
                            Map<String, Object> hostMgrConfig = (Map<String, Object>) map.get("host-mgr");
                            String sshKey = hostMgrConfig.get("ssh-key").toString();

                            writeSSHKey(sshKey);
                        } catch (Exception e) {
                            log.info("!!!receiveConfigInfo fail:{}", e.getMessage());
                        }
                    }
                };

        this.nacosConfigManager
                .getConfigService()
                .addListener("application-common.yml", "DEFAULT_GROUP", listener);
    }

    private void writeSSHKey(String sshKey) throws Exception {
        String filePath = System.getProperty("user.home") + "/.ssh/id_rsa";
        File file = new File(filePath);
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter writer = new BufferedWriter(fileWriter);
        writer.write(sshKey);
        writer.close();

        try {
            Set<PosixFilePermission> permissions = PosixFilePermissions.fromString("rw-------");
            Files.setPosixFilePermissions(file.toPath(), permissions);
            //log.info("文件权限修改成功！");
        } catch (Exception e) {
            log.error("文件权限修改失败：" + e.getMessage());
        }
    }
}