package com.androiddevelopersguide.core.scenario

/** Failure modes the [com.androiddevelopersguide.data.remote.FakeApiService] can simulate. */
enum class ApiScenario {
    SUCCESS,
    NETWORK_ERROR,
    TIMEOUT,
    SERVER_ERROR,
    INVALID_JSON
}

/** Failure modes the [com.androiddevelopersguide.data.local.daos.FakePostDao] can simulate. */
enum class DbScenario {
    SUCCESS,
    EMPTY,
    READ_ERROR,
    WRITE_ERROR
}
