package com.imdevil.shot.datastore.user

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import com.imdevil.shot.core.datastore.TencentUserProfile
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

/**
 * An [androidx.datastore.core.Serializer] for the [TencentUserProfile] proto.
 */
class TencentUserProfileSerializer @Inject constructor() : Serializer<TencentUserProfile> {
    override val defaultValue: TencentUserProfile = TencentUserProfile.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): TencentUserProfile =
        try {
            // readFrom is already called on the data store background thread
            TencentUserProfile.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }

    override suspend fun writeTo(t: TencentUserProfile, output: OutputStream) {
        // writeTo is already called on the data store background thread
        t.writeTo(output)
    }
}
