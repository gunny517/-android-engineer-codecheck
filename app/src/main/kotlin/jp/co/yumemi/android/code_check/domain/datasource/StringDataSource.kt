package jp.co.yumemi.android.code_check.domain.datasource

import android.content.Context
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class StringDataSource @Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun getString(@StringRes res: Int, value: String?): String {
        return if(value == null){
            context.getString(res)
        } else {
            context.getString(res, value)
        }
    }

}