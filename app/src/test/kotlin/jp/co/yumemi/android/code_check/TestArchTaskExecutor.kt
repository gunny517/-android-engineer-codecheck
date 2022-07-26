package jp.co.yumemi.android.code_check

import androidx.arch.core.executor.TaskExecutor

class TestArchTaskExecutor : TaskExecutor() {

    override fun executeOnDiskIO(runnable: Runnable) {
        runnable.run()
    }

    override fun isMainThread(): Boolean {
        return true
    }

    override fun postToMainThread(runnable: Runnable) {
        runnable.run()
    }
}