package edu.farmingdale.pizzapartybottomnavbar

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlinx.coroutines.launch
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon

@Composable
fun NavigationGraph(navController: NavHostController, onBottomBarVisibilityChanged: (Boolean) -> Unit) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()


    // used animation because it looked choppy without it
    // set white because it looks better as you cant see text if its transparent background
    val backgroundColor by animateColorAsState(
        targetValue = if (drawerState.isOpen) Color.White else Color.Transparent
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            drawermenu(
                backgroundColor = backgroundColor,
                onItemClicked = { route ->
                    scope.launch {
                        drawerState.close() // Close the drawer after clicking an item
                    }
                    navController.navigate(route)
                }
            )
        }
    ) {
        NavHost(navController, startDestination = BottomNavigationItems.Welcome.route) {
            composable(BottomNavigationItems.Welcome.route) {
                onBottomBarVisibilityChanged(false)
                SplashScreen(navController = navController)
            }
            composable(BottomNavigationItems.PizzaScreen.route) {
                onBottomBarVisibilityChanged(true)
                PizzaPartyScreen()
            }
            composable(BottomNavigationItems.GpaAppScreen.route) {
                onBottomBarVisibilityChanged(true)
                GpaAppScreen()
            }
            composable(BottomNavigationItems.Screen3.route) {
                onBottomBarVisibilityChanged(true)
                Screen3()
            }
        }
    }
}

@Composable
fun drawermenu(backgroundColor: Color,onItemClicked: (String) -> Unit) {
   Column(modifier = Modifier.background(color = backgroundColor).fillMaxSize().padding(16.dp)) {
        DrawerItem(
            label = "Pizza Party",
            icon = Icons.Outlined.ShoppingCart,
            onClick = { onItemClicked(BottomNavigationItems.PizzaScreen.route) }
        )
        DrawerItem(
            label = "GPA Calculator",
            icon = Icons.Outlined.Info,
            onClick = { onItemClicked(BottomNavigationItems.GpaAppScreen.route) }
        )
        DrawerItem(
            label = "Screen 3",
            icon = Icons.Outlined.Person,
            onClick = { onItemClicked(BottomNavigationItems.Screen3.route) }
        )

   }
}

@Composable
fun DrawerItem(label: String,  icon: androidx.compose.ui.graphics.vector.ImageVector, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = label, style = MaterialTheme.typography.bodyMedium)
    }
}






// ToDo 8: This is the homework:
// add a drawer navigation as described in drawable drawermenu.png
// Improve the design and integration of the app for 5 extra credit points.