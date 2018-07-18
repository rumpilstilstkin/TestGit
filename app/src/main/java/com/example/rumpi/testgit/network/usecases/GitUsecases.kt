package com.example.rumpi.testgit.network.usecases

import com.example.rumpi.testgit.network.Endpoints


///////////////////////////////////////////////////////////////////////////
// Git Usecases
///////////////////////////////////////////////////////////////////////////

class GitUsecases(
        internal val endpoints: Endpoints
) : UserUsecases by UserUsecasesImpl(endpoints),
    RepUsecases by RepUsecasesImpl(endpoints)