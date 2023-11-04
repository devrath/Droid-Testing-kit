package com.istudio.network.utils

// Reference:-> https://docs.openexchangerates.org/reference/errors
object NetworkStatusErrorCodes {

    object NotFound {
        const val code = 404
        const val message = "Client requested a non-existent resource/route"
    }

    object InvalidAppId {
        const val code = 401
        const val message = "Invalid App ID"
    }

    object NotAllowed {
        const val code = 429
        const val message = "Client doesn’t have permission to access requested route/feature"
    }

    object AccessRestricted {
        const val code = 403
        const val message = "Access restricted for repeated over-use (status: 429), or other reason given in ‘description’ (403)."
    }

    object InvalidBase {
        const val code = 400
        const val message = "Only dollar as a user currency is supported \n\n Please upgrade to a paid service"
    }


}