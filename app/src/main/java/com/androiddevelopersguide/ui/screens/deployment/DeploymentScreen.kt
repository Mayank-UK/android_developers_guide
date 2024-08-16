package com.androiddevelopersguide.ui.screens.deployment

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.Links
import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.components.Section

@Composable
fun DeploymentScreen(
    navController: NavController,
    viewModel: DeploymentViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Deployment",
            )
        },
        mobileUI = {
            TestDeployment()
            ProdDeployment()
        }
    )
}

@Composable
fun TestDeployment() {
    Section(
        title = "Test deployment",
        description = """
            In android or any other software it is very important that it should have configuration to switch between test and prod environment.
            Test build will use other set of resources like API, etc and may also contain some additional UI for testing purpose.
            
            Deployment for test build can be done by either sharing the apk file manually or putting in the google play console under testing category (preferred approach).
        """.trimIndent(),
    )
}

@Composable
fun ProdDeployment() {
    Section(
        title = "Prod deployment",
        description = """
            After testing is done on test build, then prod build can be created and given for testing in prod environment. After successfully testing, this build will be released to production.
                   
            Deployment for prod build can be done after it's testing is complete in play console and there is no bugs. There are options in google console to release a tested build to production.
            
            This production build will now be reviewed by google and released after it conforms to their policies.
        """.trimIndent(),
    )
}

@Preview
@Composable
fun DeploymentScreenPreview() {
    DeploymentScreen(navController = rememberNavController())
}