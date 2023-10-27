package com.istudio.code.presentation

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNull
import assertk.assertions.isTrue
import com.istudio.code.domain.UserRepositoryFake
import com.istudio.code.utils.MainCoroutineExtension
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(MainCoroutineExtension::class)
class ProfileViewModelTest {

    // SUT: Current system under test
    private lateinit var viewModel : ProfileViewModel
    private lateinit var repository : UserRepositoryFake


    @BeforeEach
    fun setUp(){

        repository = UserRepositoryFake()
        viewModel = ProfileViewModel(
            repository = repository,
            savedStateHandle = SavedStateHandle(
                initialState = mapOf(
                    "userId" to repository.profile.user.id
                )
            )
        )
    }

    @Test
    fun `Test loading state updates`() = runTest{
        viewModel.state.test {
            // This will suspend the test case until there is emission of first item
            val emission1 = awaitItem()
            assertThat(emission1.isLoading).isFalse()
            // Perform next action in the view-model
            viewModel.loadProfile()
            // Check the next emission -- Until the next emission the test block is blocked
            val emission2 = awaitItem()
            assertThat(emission2.isLoading).isTrue()
            // Check the next emission which is final emission
            val emission3 = awaitItem()
            assertThat(emission3.isLoading).isFalse()
        }
    }

}