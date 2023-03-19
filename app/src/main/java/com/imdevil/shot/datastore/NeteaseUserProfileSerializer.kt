package com.imdevil.shot.datastore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

/**
 * An [androidx.datastore.core.Serializer] for the [NeteaseUserProfile] proto.
 */
class NeteaseUserProfileSerializer @Inject constructor() : Serializer<NeteaseUserProfile> {
    override val defaultValue: NeteaseUserProfile = NeteaseUserProfile.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): NeteaseUserProfile =
        try {
            // readFrom is already called on the data store background thread
            @Suppress("BlockingMethodInNonBlockingContext")
            NeteaseUserProfile.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }

    override suspend fun writeTo(t: NeteaseUserProfile, output: OutputStream) {
        // writeTo is already called on the data store background thread
        @Suppress("BlockingMethodInNonBlockingContext")
        t.writeTo(output)
    }
}
