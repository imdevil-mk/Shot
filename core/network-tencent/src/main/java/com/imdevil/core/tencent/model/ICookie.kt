package com.imdevil.core.tencent.model

interface ICookie {
    fun getCookie(): String
}

class AndroidCookieManager : ICookie {
    override fun getCookie(): String {
        return "wxopenid=;Domain=.qq.com;Path=/;Expires=1.698936817246802E9;ts_uid=9754041981;Domain=.y.qq.com;Path=/;Expires=1.725720826178669E9;ts_refer=jsososo.github.io/;Domain=.y.qq.com;Path=/;Expires=1.706712732E9;qqmusic_key=Q_H_L_5YTml_RV-iwTuTZwXXx7TNIXHo81B87JQ-0Sug0WDsjZWP39YaqJzxQ;Domain=.qq.com;Path=/;Expires=1.691247217246786E9;qm_keyst=Q_H_L_5YTml_RV-iwTuTZwXXx7TNIXHo81B87JQ-0Sug0WDsjZWP39YaqJzxQ;Domain=.y.qq.com;Path=/;Expires=1.691247217246756E9;qm_keyst=Q_H_L_5YTml_RV-iwTuTZwXXx7TNIXHo81B87JQ-0Sug0WDsjZWP39YaqJzxQ;Domain=.qq.com;Path=/;Expires=1.691247217246692E9;ptcz=1fc68f1d48356fa3422486c458d902ab7f61f62176e6fd5bfe83529499ac0fa4;Domain=.qq.com;Path=/;Expires=1.725720814547981E9;psrf_qqunionid=F75CCB10B7A00F6C368C14AC79318077;Domain=.qq.com;Path=/;Expires=1.69893681724674E9;psrf_qqopenid=E05B4B0F8E9D79F3891A18E22FD13219;Domain=.qq.com;Path=/;Expires=1.698936817246829E9;psrf_qqaccess_token=5D4FCCC2A00818802C6D2CD3AC96FE64;Domain=.qq.com;Path=/;Expires=1.698936817246638E9;psrf_musickey_createtime=1691160824;Domain=.qq.com;Path=/;Expires=1.691247217246616E9;psrf_access_token_expiresAt=1698936824;Domain=.qq.com;Path=/;Expires=1.69893681724672E9;fqm_pvqid=41adca2b-b883-4ee7-890a-cbfaf5ecfb1a;Domain=.qq.com;Path=/;Expires=1.72572073148465E9;ptui_loginuin=516959708;Domain=.qq.com;Path=/;Expires=1.693752796E9;fqm_sessionid=1e4a049a-1d08-4fdd-b13f-2dd3e330e531;Domain=.qq.com;Path=/;Expires=null;pgv_info=ssid=s1847636220;Domain=.qq.com;Path=/;Expires=null;pgv_pvid=350689875;Domain=.qq.com;Path=/;Expires=1.725720826178455E9;uin=516959708;Domain=.qq.com;Path=/;Expires=1.698936817246773E9;psrf_qqrefresh_token=EA72C1B3B97C1002844A1D2D126A4937;Domain=.qq.com;Path=/;Expires=1.698936817246844E9;_qpsvr_localtk=0.7286993984217942;Domain=.qq.com;Path=/;Expires=null;RK=MBXtC8llGY;Domain=.qq.com;Path=/;Expires=1.698322149915915E9;wxrefresh_token=;Domain=.qq.com;Path=/;Expires=1.698936817246666E9;euin=7K6sNK4q7inF;Domain=.qq.com;Path=/;Expires=1.698936817246679E9;tmeLoginType=2;Domain=.qq.com;Path=/;Expires=1.698936817246815E9;wxunionid=;Domain=.qq.com;Path=/;Expires=1.698936817246539E9;login_type=1;Domain=.qq.com;Path=/;Expires=null;"
    }
}
