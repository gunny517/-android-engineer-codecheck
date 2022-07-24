package jp.co.yumemi.android.code_check.ui.item_list

import io.mockk.coVerify
import io.mockk.mockk
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

    val dispatcher = TestCoroutineDispatcher()

    beforeGroup {
        Dispatchers.setMain(dispatcher)
    }

    lateinit var viewModel: ItemListViewModel

    describe("検索の機能") {
        beforeEachTest {
            viewModel = ItemListViewModel(
                getRepositorySearchResultUseCase = mockk(),
            )
        }
        it("指定したキーワードで検索が実行されること") {
            viewModel.innerSearch("keyword")
            coVerify {
                viewModel.getRepositorySearchResultUseCase.invoke("keyword")
            }
        }
    }

    afterGroup {
        Dispatchers.resetMain()
    }

})