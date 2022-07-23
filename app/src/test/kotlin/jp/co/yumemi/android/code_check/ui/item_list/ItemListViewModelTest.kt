package jp.co.yumemi.android.code_check.ui.item_list

import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import jp.co.yumemi.android.code_check.domain.model.Item
import jp.co.yumemi.android.code_check.usecase.GetRepositorySearchResultUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(JUnitPlatform::class)
object ItemListViewModelTest : Spek({

    lateinit var viewModel: ItemListViewModel

    beforeGroup {
        Dispatchers.setMain(TestCoroutineDispatcher())
    }

    describe("検索の機能") {
        beforeEachTest {
            val getRepositorySearchResultUseCase = mockk<GetRepositorySearchResultUseCase> {
                coEvery {
                    this@mockk.invoke(any(), captureLambda<(Result<List<Item>>) -> Unit>())
                } answers {
                    lambda<(Result<List<Item>>) -> Unit>().captured.invoke(
                        Result.success(
                            listOf(
                                Item (
                                    name = "JetBrains/kotlin",
                                    language = "Written in Kotlin",
                                    stargazersCount = 38530L,
                                    forksCount = 10L,
                                    watchersCount = 11L,
                                    openIssuesCount = 12L,
                                    ownerIconUrl = "https://example.com/image.png"
                                )
                            )
                        )
                    )
                }
            }
            viewModel = ItemListViewModel(
                getRepositorySearchResultUseCase = getRepositorySearchResultUseCase,
            )
        }
        it("検索結果がセットされること") {
            viewModel.innerSearch("query")
            assertThat(viewModel.searchResult.value).hasSize(1)
        }
    }

    afterGroup {
        Dispatchers.resetMain()
    }

})