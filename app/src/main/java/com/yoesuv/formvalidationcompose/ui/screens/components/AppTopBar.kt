package com.yoesuv.formvalidationcompose.ui.screens.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yoesuv.formvalidationcompose.ui.theme.FormValidationTheme

private val fontSize = 18.sp
private val fontWeight = FontWeight.SemiBold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title: String,
    canBack: Boolean = true,
    navigateUp: () -> Unit = {}
) {
    if (canBack) {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = Color.White
            ),
            title = {
                Text(text = title, fontSize = fontSize, fontWeight = fontWeight)
            },
            navigationIcon = {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                        contentDescription = "action navigation",
                        tint = Color.White
                    )
                }
            }
        )
    } else {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = Color.White
            ),
            title = {
                Text(text = title, fontSize = fontSize, fontWeight = fontWeight)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AppTopBarPreview() {
    FormValidationTheme {
        AppTopBar(title = "Form Validation", canBack = true)
    }
}