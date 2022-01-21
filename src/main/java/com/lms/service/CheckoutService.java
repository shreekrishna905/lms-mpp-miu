package com.lms.service;

import com.lms.business.CheckoutRecord;

public interface CheckoutService {

    CheckoutRecord checkout(String memberId, String isbn);

}
