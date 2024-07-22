package cl.test.supertienda.model

/**
 * Class that represents a repository request response.
 * @param isSuccessful Whether the request was successful or not
 * @param body If the request is successful, contains the request response data
 * @param error If the request is not successful, contains an error message
 */
data class Response<T>(val isSuccessful: Boolean, val body: T? = null, val error: String? = null)
