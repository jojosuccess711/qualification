package com.bdjbd.service.common;

import com.bdjbd.Message;

public interface UploadService {
     Message<String> uploadToObs(String bucketName, String objectKey, String file);
}
