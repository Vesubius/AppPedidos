package com.vesudev.pedidosapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.vesudev.pedidosapp.navigation.FirtsNavigationGraph
import com.vesudev.pedidosapp.ui.theme.PedidosAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {


            PedidosAppTheme {

                FirtsNavigationGraph()
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    FirtsNavigationGraph()
}

