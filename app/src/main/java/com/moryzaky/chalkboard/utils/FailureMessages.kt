package com.moryzaky.chalkboard.utils

import android.content.Context
import com.moryzaky.chalkboard.R
import com.moryzaky.chalkboard.exception.Failure

/**
 * Created by Morteza Khosravinejad on 16/07/2022.
 */

object FailureMessages {
    fun getProperFailureMessage(context: Context, failure: Failure): String {
        return when (failure) {
            is Failure.NetworkConnection -> {
                context.getString(R.string.error_network)
            }
            is Failure.DatabaseError -> {
                context.getString(R.string.error_database)
            }
            is Failure.ServerError -> {
                String.format(
                    context.getString(R.string.error_server),
                    failure.code,
                    failure.message
                )
            }
            else -> {
                context.getString(R.string.error_unknown)
            }
        }
    }
}