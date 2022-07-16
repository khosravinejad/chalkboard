package com.moryzaky.chalkboard.exception

/**
 * Created by Morteza Khosravinejad on 16/07/2022.
 */

sealed class Failure {
    object NetworkConnection : Failure()
    object DatabaseError : Failure()
    data class ServerError(val code: Int, val message: String) : Failure()
    data class UnknownError(val throwable: Throwable) : Failure()
}