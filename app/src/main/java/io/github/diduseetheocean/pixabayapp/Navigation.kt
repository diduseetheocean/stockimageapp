package io.github.diduseetheocean.pixabayapp

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import io.github.diduseetheocean.pixabayapp.data.model.SearchImagesViewModel
import io.github.diduseetheocean.pixabayapp.ui.components.view.ImageDetailsView
import io.github.diduseetheocean.pixabayapp.ui.components.view.SearchView

@ExperimentalCoilApi
@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    val viewModel: SearchImagesViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = Route.SearchView.route,
        modifier = modifier
    ) {
        composable(Route.SearchView.route) {
            SearchView(viewModel) {
                navController.navigate("${Route.ImageDetailsView.route}/${it}")
            }
        }

        composable(
            route = "${Route.ImageDetailsView.route}/{imageId}",
            arguments = listOf(navArgument("imageId") { type = NavType.LongType })
        ) { entry ->

            val id = entry.arguments?.getLong("imageId")
            val image = id?.let { viewModel.findImage(it) }
            if (image != null) {
                ImageDetailsView(image)
            }
        }
    }
}

sealed class Route(val route: String, @StringRes val resourceId: Int) {
    object SearchView : Route("search_view", R.string.search_screen)
    object ImageDetailsView : Route("image_details_view", R.string.image_details_screen)
}