package jp.co.yumemi.android.code_check.domain.datasource

import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import jp.co.yumemi.android.code_check.R
import jp.co.yumemi.android.code_check.TopActivity
import jp.co.yumemi.android.code_check.domain.model.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.IOException
import java.util.*
import javax.inject.Inject

class GitHubApiDataSource @Inject constructor() {

    suspend fun getRepositorySearchResult(query: String,  callback: (kotlin.Result<List<Item>>) -> Unit) {
        withContext(Dispatchers.IO){
            ENDPOINT.httpGet(
                listOf(Pair("q", query))
            ).header(
                mapOf(Pair("Accept", "application/vnd.github.v3+json"))
            ).responseString { _, _, result ->
                when (result) {
                    is Result.Success -> {
                        callback(kotlin.Result.success(parseResponse(result.value)))
                    }
                    is Result.Failure -> {
                        callback(kotlin.Result.failure(IOException("Unknown")))
                    }
                }
            }
        }
    }

    private fun parseResponse(json: String): List<Item> {
        val jsonBody = JSONObject(json)
        val jsonItems = jsonBody.optJSONArray("items")!!
        val items = mutableListOf<Item>()

        /**
         * アイテムの個数分ループする
         */
        for (i in 0 until jsonItems.length()) {
            val jsonItem = jsonItems.optJSONObject(i)!!
            val name = jsonItem.optString("full_name")
            val ownerIconUrl = jsonItem.optJSONObject("owner")!!.optString("avatar_url")
            val language = jsonItem.optString("language")
            val stargazersCount = jsonItem.optLong("stargazers_count")
            val watchersCount = jsonItem.optLong("watchers_count")
            val forksCount = jsonItem.optLong("forks_count")
            val openIssuesCount = jsonItem.optLong("open_issues_count")

            items.add(
                Item(
                    name = name,
                    ownerIconUrl = ownerIconUrl,
                    language = language,
                    stargazersCount = stargazersCount,
                    watchersCount = watchersCount,
                    forksCount = forksCount,
                    openIssuesCount = openIssuesCount
                )
            )
        }
        TopActivity.lastSearchDate = Date()
        return items.toList()
    }

    companion object {
        const val ENDPOINT = "https://api.github.com/search/repositories"
    }
}