package jp.co.yumemi.android.code_check.ui.item_detail

import androidx.lifecycle.SavedStateHandle
import io.mockk.every
import io.mockk.mockk
import jp.co.yumemi.android.code_check.domain.model.Item
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import com.google.common.truth.Truth.assertThat

@RunWith(JUnitPlatform::class)
object ItemDetailViewModelTest : Spek({

    lateinit var viewModel: ItemDetailViewModel

    val savedStateHandle: SavedStateHandle = mockk {
        every {
            get<Item>("item")
        } returns Item (
            name = "JetBrains/kotlin",
            language = "Written in Kotlin",
            stargazersCount = 38530L,
            forksCount = 10L,
            watchersCount = 11L,
            openIssuesCount = 12L,
            ownerIconUrl = "https://example.com/image.png"
        )
    }

    describe("初期化の正常性") {
        beforeEachTest {
            viewModel = ItemDetailViewModel(
                savedStateHandle = savedStateHandle
            )
        }
        it("パラメータを取得している事") {
            assertThat(viewModel.item).isNotNull()
        }
        it("表示用の値が設定されること") {
            assertThat(viewModel.name.value).isEqualTo("JetBrains/kotlin")
            assertThat(viewModel.language.value).isEqualTo("Written in Kotlin")
            assertThat(viewModel.stargazersCount.value).isEqualTo(38530L)
            assertThat(viewModel.forksCount.value).isEqualTo(10L)
            assertThat(viewModel.watchersCount.value).isEqualTo(11L)
            assertThat(viewModel.openIssuesCount.value).isEqualTo(12L)
        }
    }
})