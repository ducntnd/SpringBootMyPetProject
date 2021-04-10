$('#form-login').on('submit', function (e) {
    e.preventDefault();
    $('.invalid-feedback').css('display', 'none');
    let emailFormat = new RegExp(/^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/);


    let isValid = true;
    let signInEmail = $('.sign-in-email');
    let signInEmailValue = signInEmail.val();
    let signInEmailInvalid = $('.sign-in-email').next();
    let signInPassword = $('.sign-in-password');
    let signInPasswordValue = signInPassword.val();
    let signInPasswordInvalid = $('.sign-in-password').next();

    if (signInEmailValue == '') {
        signInEmailInvalid.css('display', 'block');
        signInEmailInvalid.html('Vui lòng nhập email');
        isValid = false;
    } else if (!emailFormat.test(signInEmailValue)) {
        signInEmailInvalid.css('display', 'block');
        signInEmailInvalid.html('Email không hợp lệ');
        isValid = false;
    }
    if (signInPasswordValue == "") {
        signInPasswordInvalid.css('display', 'block');
        signInPasswordInvalid.html('Vui lòng nhập password')
        isValid = false;
    }

    if (isValid == true) {
        let req = {
            email: signInEmailValue,
            password: signInPasswordValue
        };

        var myJSON = JSON.stringify(req);
        $.ajax({
            url: '/api/login',
            type: 'POST',
            data: myJSON,
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                var json = $.parseJSON(data);
                signedValidate(true, json.fullname);
                $('.modal').modal('hide');
            },
            error: function (data) {
                alert(data);
            }
        });
    }
});

$('#form-register').on('submit', function (e) {
    e.preventDefault();
    let isValid = true;
    $('.invalid-feedback').css('display', 'none');
    let emailFormat = new RegExp(/^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/);

    let fullName = $('.sign-up-full-name');
    let fullNameValue = fullName.val();
    let fullNameInvalid = fullName.next();
    let email = $('.sign-up-email');
    let emailValue = email.val();
    let emailInvalid = email.next();
    let password = $('.sign-up-password');
    let passwordValue = password.val();
    let passwordInvalid = password.next();
    let confirmPassword = $('.sign-up-confirm-password');
    let confirmPasswordValue = confirmPassword.val();
    let confirmPasswordInvalid = confirmPassword.next();

    // Validate name
    if (fullNameValue == "") {
        fullNameInvalid.css('display', 'block');
        fullNameInvalid.html('Vui lòng nhập họ tên');
        isValid = false;
    }

    if (emailValue == "") {
        emailInvalid.css('display', 'block');
        emailInvalid.html('Vui lòng nhập email');
        isValid = false;
    } else if (!emailFormat.test(emailValue)) {
        emailInvalid.css('display', 'block');
        emailInvalid.html('Email không hợp lệ');
        isValid = false;
    }

    // Validate password
    if (passwordValue == "") {
        passwordInvalid.css('display', 'block');
        passwordInvalid.html('Vui lòng nhập password');
        isValid = false;
    }

    // Validate confirm password
    if (confirmPasswordValue == "") {
        confirmPasswordInvalid.css('display', 'block');
        confirmPasswordInvalid.html('Vui lòng xác nhận lại password');
        isValid = false;
    } else if (confirmPasswordValue !== passwordValue) {
        confirmPasswordInvalid.css('display', 'block');
        confirmPasswordInvalid.html('Mật khẩu và xác nhận mật khẩu không giống nhau');
        isValid = false;
    }

    if (isValid == true) {
        let req = {
            fullname:fullNameValue,
            email: emailValue,
            password: passwordValue
        };
        var myJSON = JSON.stringify(req);
        $.ajax({
            url: '/api/register',
            type: 'POST',
            data: myJSON,
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                var json = $.parseJSON(data);
                $('.modal').modal('hide');
                signedValidate(true, json.fullname);
            },
            error: function (data) {
                alert(data);
            },
        });
    }
});


function signedValidate(status, fullname) {
    if (status == true) {
        let signedLink = `<a href="/post" class="button-round">Viết bài</a>&nbsp;&nbsp;
                    <svg xmlns:xlink="http://www.w3.org/1999/xlink" height="21px" version="1.1" viewBox="0 0 18 21" width="18px" xmlns="http://www.w3.org/2000/svg">
\t\t\t\t\t\t<g>
\t\t\t\t\t\t\t<path d="M7.0119539,3.2075415 L7.40027579,2.72017306 C4.28893812,3.43312094 2.01737858,6.27353318 2.01737858,9.52932402 L2.01737858,15.0482984 L2.16302114,14.6955509 L0.863966411,16.0005345 C0.162266719,16.7054368 0.531485356,17.5551982 1.51846633,17.5551982 L16.5021867,17.5551982 C17.4956337,17.5551982 17.8593786,16.7064336 17.1566866,16.0005345 L15.8576319,14.6955509 L16.0032744,15.0482984 L16.0032744,9.52932402 C16.0032744,6.27355389 13.7316876,3.43312678 10.620379,2.72017347 L11.0086991,3.2075415 L11.0086991,2.50517483 C11.0086991,1.37651058 10.1361479,0.5 9.0103265,0.5 C7.88450511,0.5 7.0119539,1.37651058 7.0119539,2.50517483 L7.0119539,3.2075415 Z M9.0103265,1.5 C9.5828471,1.5 10.0086991,1.92778437 10.0086991,2.50517483 L10.0086991,3.2075415 L10.0086991,3.60592637 L10.3970192,3.69490954 C13.0546055,4.30389283 15.0032744,6.74053842 15.0032744,9.52932402 L15.0032744,15.0482984 L15.0032744,15.2547386 L15.148917,15.4010459 L16.4479717,16.7060295 C16.5179593,16.7763365 16.6127299,16.5551982 16.5021867,16.5551982 L1.51846633,16.5551982 C1.41129622,16.5551982 1.50595615,16.7730592 1.5726813,16.7060295 L2.87173603,15.4010459 L3.01737858,15.2547386 L3.01737858,15.0482984 L3.01737858,9.52932402 C3.01737858,6.74051695 4.96602042,4.30388871 7.62363201,3.69490995 L8.0119539,3.60592788 L8.0119539,3.2075415 L8.0119539,2.50517483 C8.0119539,1.92778437 8.43780589,1.5 9.0103265,1.5 Z"></path>
\t\t\t\t\t\t\t<rect height="1.66015625" width="2.57819185" x="7.71090407" y="18.4190193"></rect>
\t\t\t\t\t\t</g>
\t\t\t\t\t</svg>&nbsp;&nbsp;
<a class="username nav-link dropdown-toggle" href="#" id="userInfor" role="button"
                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
\t\t\t\t\t<span class="text">` + fullname + `</span>
\t\t\t\t\t<span class="icon">
\t\t\t\t\t\t<svg height="10" viewBox="0 0 1000 1000" width="10" x="0px" y="0px">
\t\t\t\t\t\t\t<g><path d="M133.1,254.6l366.8,352.1L871.4,250l0,0c12.6-11.7,29.7-18.8,48.6-18.8c38.7,0,70,30.1,70,67.2c0,18.1-7.5,34.6-19.6,46.6l0,0l-420,403.2l0,0c-12.7,12.7-30.6,20.6-50.4,20.6c0,0-0.1,0-0.2,0c-0.1,0-0.1,0-0.2,0c-19.8,0-37.6-7.9-50.4-20.6l0,0l-420-403.2l0.2-0.2c-12-12.1-19.5-28.4-19.5-46.5c0-37.1,31.3-67.2,70-67.2C101.2,231.2,120.2,240.3,133.1,254.6z"></path></g>
\t\t\t\t\t\t</svg>
\t\t\t\t\t</span>
\t\t\t\t</a>`;

        $('.account').html('');
        $('.account').append(signedLink);
    } else {
        let notSignedLink = `<button type="button" data-toggle="modal" data-target="#loginModal">
                    Đăng nhập
                </button>&nbsp;
                <p style="margin-bottom: 0 !important;">/</p>&nbsp;
                <button type="button" data-toggle="modal" data-target="#signInModal">
                    Đăng ký
                </button>&nbsp;`;

        $('.account').html('');
        $('.account').append(notSignedLink);
    }
}