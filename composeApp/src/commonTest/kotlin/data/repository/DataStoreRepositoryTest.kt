package data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.mutablePreferencesOf
import androidx.datastore.preferences.core.stringPreferencesKey
import dev.mokkery.answering.returns
import dev.mokkery.every
import dev.mokkery.mock
import dev.mokkery.verify
import dev.mokkery.verifyNoMoreCalls
import domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class DataStoreRepositoryTest {
    private val dataStore = mock<DataStore<Preferences>>()

    private lateinit var repository: DataStoreRepository

    private val testKey = "testKey"
    private val testIntValue = 5
    private val testStringValue = "testStringValue"
    private val testBooleanValue = true
    private val testFloatValue = 3.14f

    @BeforeTest
    fun beforeTest() {
        repository = DataStoreRepository(dataStore)
    }

    @Test
    fun `calls datastore and emits correct flow of Int`() = runTest {
        // GIVEN preference is in datastore
        every {
            dataStore.data
        } returns flowOf(
            mutablePreferencesOf(intPreferencesKey(testKey) to testIntValue),
        )

        // WHEN use case is called
        val resultFlow = repository.emitIntPreference(testKey, 0).getOrNull()

        // THEN datastore is called once and emitted flow is correct
        verify {
            dataStore.data
        }
        assertNotNull(resultFlow, "Emitted flow should not be null")
        val emittedValue = resultFlow.firstOrNull()
        assertEquals(Result.success(testIntValue).getOrNull(), emittedValue)
        verifyNoMoreCalls(dataStore)
    }

    @Test
    fun `calls datastore and emits correct flow of String`() = runTest {
        // GIVEN preference is in datastore
        every {
            dataStore.data
        } returns flowOf(
            mutablePreferencesOf(stringPreferencesKey(testKey) to testStringValue),
        )

        // WHEN use case is called
        val resultFlow = repository.emitStringPreference(testKey, "").getOrNull()

        // THEN datastore is called once and emitted flow is correct
        verify {
            dataStore.data
        }
        assertNotNull(resultFlow, "Emitted flow should not be null")
        val emittedValue = resultFlow.firstOrNull()
        assertEquals(Result.success(testStringValue).getOrNull(), emittedValue)
        verifyNoMoreCalls(dataStore)
    }

    @Test
    fun `calls datastore and emits correct flow of Boolean`() = runTest {
        // GIVEN preference is in datastore
        every {
            dataStore.data
        } returns flowOf(
            mutablePreferencesOf(booleanPreferencesKey(testKey) to testBooleanValue),
        )

        // WHEN use case is called
        val resultFlow = repository.emitBooleanPreference(testKey, false).getOrNull()

        // THEN datastore is called once and emitted flow is correct
        verify {
            dataStore.data
        }
        assertNotNull(resultFlow, "Emitted flow should not be null")
        val emittedValue = resultFlow.firstOrNull()
        assertEquals(Result.success(testBooleanValue).getOrNull(), emittedValue)
        verifyNoMoreCalls(dataStore)
    }

    @Test
    fun `calls datastore and emits correct flow of Float`() =
        runTest {
            // GIVEN preference is in datastore
            every {
                dataStore.data
            } returns flowOf(
                mutablePreferencesOf(floatPreferencesKey(testKey) to testFloatValue),
            )

            // WHEN use case is called
            val resultFlow = repository.emitFloatPreference(testKey, 0.0f).getOrNull()

            // THEN datastore is called once and emitted flow is correct
            verify {
                dataStore.data
            }
            assertNotNull(resultFlow, "Emitted flow should not be null")
            val emittedValue = resultFlow.firstOrNull()
            assertEquals(Result.success(testFloatValue).getOrNull(), emittedValue)
            verifyNoMoreCalls(dataStore)
        }
}
