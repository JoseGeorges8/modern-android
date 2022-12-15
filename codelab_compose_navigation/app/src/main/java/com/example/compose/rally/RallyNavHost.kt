package com.example.compose.rally

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.compose.rally.ui.accounts.AccountsScreen
import com.example.compose.rally.ui.accounts.SingleAccountScreen
import com.example.compose.rally.ui.bills.BillsScreen
import com.example.compose.rally.ui.overview.OverviewScreen

@Composable
fun RallyNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Overview.route,
        modifier = modifier
    ) {
        composable(route = Overview.route) {
            OverviewScreen(
                onClickSeeAllAccounts = {
                    navController.navigateSingleTopTo(Accounts.route)
                },
                onClickSeeAllBills = {
                    navController.navigateSingleTopTo(Bills.route)
                },
                onAccountClick = { accountType ->
                    navController.navigateToSingleAccount(accountType)
                }
            )
        }
        composable(route = Accounts.route) {
            AccountsScreen(
                onAccountClick = { accountType ->
                    navController.navigateToSingleAccount(accountType)
                }
            )
        }
        composable(route = Bills.route) {
            BillsScreen()
        }
        composable(
            // To pass the argument alongside your route when navigating, you need to append them together
            route = SingleAccount.routeWithArgs,
            // to make this composable aware that it should accept arguments you define its arguments parameter
            arguments = SingleAccount.arguments,
            deepLinks = SingleAccount.deepLinks
        ) { navBackStackEntry ->
            // In Compose Navigation, each NavHost composable function has access to the current
            // NavBackStackEntry - a class which holds the information on the current route and
            // passed arguments of an entry in the back stack. You can use this to get the
            // required arguments list from navBackStackEntry and then search and retrieve
            // the exact argument you need, to pass it down further to your composable screen.
            val accountType =
                navBackStackEntry.arguments?.getString(SingleAccount.accountTypeArg)
            SingleAccountScreen(accountType)
        }
    }
}

// to make sure there will be at most one copy of a given destination on the top of the back stack,
// Compose Navigation API provides a launchSingleTop flag you can pass to your navController.navigate()
// you can extract this behaviour into a helper extension:
fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        popUpTo(
            // pop up to the start destination of the graph to avoid building up a large stack
            // of destinations on the back stack as you select tabs
            // In Rally, this would mean that pressing the back arrow from any destination would pop
            // the entire back stack to Overview
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        launchSingleTop = true
        // determines whether this navigation action should restore any state previously saved by
        // PopUpToBuilder.saveState or the popUpToSaveState attribute.
        // Note that, if no state was previously saved with the destination ID being navigated to, this has no effect
        restoreState = true
    }

private fun NavHostController.navigateToSingleAccount(accountType: String) {
    this.navigateSingleTopTo("${SingleAccount.route}/$accountType")
}