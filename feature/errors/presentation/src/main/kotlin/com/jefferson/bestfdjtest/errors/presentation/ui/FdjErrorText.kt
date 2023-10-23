package com.jefferson.bestfdjtest.errors.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.jefferson.bestfdjtest.errors.domain.model.TechnicalError
import com.jefferson.bestfdjtest.style.R

object FdjErrorText {
    @Composable
    operator fun invoke(
        e: Throwable
    ) {
        Column(
            modifier = Modifier.background(MaterialTheme.colorScheme.error),
        ) {
            when (e) {
                is TechnicalError -> {
                    Text(
                        text = e.cause?.message.orEmpty(),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onError,
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        text = stringResource(R.string.errors_please_contact, e.supportTeam.name),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onError,
                        textAlign = TextAlign.Center,
                    )
                }

                else ->
                    Text(
                        modifier = Modifier.background(MaterialTheme.colorScheme.error),
                        text = e.message.orEmpty(),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onError,
                        textAlign = TextAlign.Center,
                    )
            }
        }
    }
}
