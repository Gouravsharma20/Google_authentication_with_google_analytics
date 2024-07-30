package wu.tutorials.google_sign_in.Presentation.sign_in

import android.content.Context
import android.content.IntentSender
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import wu.tutorials.google_sign_in.R
import java.util.concurrent.CancellationException
import kotlin.coroutines.jvm.internal.CompletedContinuation.context

class GoogleAuthUiClient(
    private val context: Context,
    private val oneTapClient:SignInClient
) {
    private val auth = Firebase.auth
    suspend fun signIn():IntentSender?{
        val result = try {
            oneTapClient.beginSignIn(
                build.SignInRequest()
            ).await()

        }catch (e:Exception) {
            e.printStackTrace()
            if (e is CancellationException)throw e
            null
        }
        return result?.pendingIntent?.intentSender
    }

}

