package jp.co.yumemi.android.code_check

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import org.spekframework.spek2.dsl.GroupBody

fun GroupBody.applyTestTaskExecutor(executor: TaskExecutor = TestArchTaskExecutor()) {
    beforeGroup {
        // AACのテスト用設定
        ArchTaskExecutor.getInstance().setDelegate(executor)
    }
    afterGroup {
        ArchTaskExecutor.getInstance().setDelegate(null)
    }
}
