package bz.stewart.bracken.shared.service

import bz.stewart.bracken.shared.web.AppServices

interface ServiceContext {
    val service: AppServices
    val windowPath: String
}