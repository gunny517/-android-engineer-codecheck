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

    fun getRepositorySearchResult(query: String,  callback: (kotlin.Result<List<Item>>) -> Unit) {
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

    private fun parseResponse(json: String): List<Item> {
        val jsonBody = JSONObject(json)
        val jsonItems = jsonBody.optJSONArray(ITEMS)!!
        val items = mutableListOf<Item>()
        for (i in 0 until jsonItems.length()) {
            jsonItems.optJSONObject(i)?.let {
                items.add(parseItem(it))
            }
        }
        TopActivity.lastSearchDate = Date()
        return items.toList()
    }

    private fun parseItem(jsonItem: JSONObject): Item {
        val name = jsonItem.optString(FULL_NAME)
        val ownerIconUrl = jsonItem.optJSONObject(OWNER)!!.optString(AVATAR_URL)
        val language = jsonItem.optString(LANGUAGE)
        val stargazersCount = jsonItem.optLong(STARGAZERS_COUNT)
        val watchersCount = jsonItem.optLong(WATCHERS_COUNT)
        val forksCount = jsonItem.optLong(FORKS_COUNT)
        val openIssuesCount = jsonItem.optLong(OPEN_ISSUES_COUNT)

        return Item(
            name = name,
            ownerIconUrl = ownerIconUrl,
            language = language,
            stargazersCount = stargazersCount,
            watchersCount = watchersCount,
            forksCount = forksCount,
            openIssuesCount = openIssuesCount
        )
    }

    companion object {
        const val ENDPOINT = "https://api.github.com/search/repositories"
        const val ITEMS = "items"
        const val FULL_NAME = "full_name"
        const val OWNER = "owner"
        const val AVATAR_URL = "avatar_url"
        const val LANGUAGE = "language"
        const val STARGAZERS_COUNT = "stargazers_count"
        const val WATCHERS_COUNT = "watchers_count"
        const val FORKS_COUNT = "forks_count"
        const val OPEN_ISSUES_COUNT = "open_issues_count"
    }
}