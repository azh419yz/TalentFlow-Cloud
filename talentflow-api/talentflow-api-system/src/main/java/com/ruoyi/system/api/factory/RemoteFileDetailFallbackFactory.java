package com.ruoyi.system.api.factory;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.system.api.RemoteFileDetailService;
import com.ruoyi.system.api.domain.SysFileDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 文件服务降级处理
 *
 * @author kenmi
 */
@Component
public class RemoteFileDetailFallbackFactory implements FallbackFactory<RemoteFileDetailService> {
    private static final Logger log = LoggerFactory.getLogger(RemoteFileDetailFallbackFactory.class);

    @Override
    public RemoteFileDetailService create(Throwable throwable) {
        log.error("系统文件服务调用失败:{}", throwable.getMessage());
        return new RemoteFileDetailService() {
            @Override
            public R<SysFileDetail> get(Long id, String source) {
                return R.fail("获取文件信息失败:" + throwable.getMessage());
            }

            @Override
            public R<SysFileDetail> getByUrl(String url, String source) {
                return R.fail("获取文件信息失败:" + throwable.getMessage());
            }

            @Override
            public R<Boolean> add(SysFileDetail sysFileDetail, String source) {
                return R.fail("保存文件信息失败:" + throwable.getMessage());
            }

            @Override
            public R<Boolean> remove(Long id, String source) {
                return R.fail("删除文件信息失败:" + throwable.getMessage());
            }

            @Override
            public R<Boolean> removeByUrl(String url, String source) {
                return R.fail("删除文件信息失败:" + throwable.getMessage());
            }
        };
    }
}
