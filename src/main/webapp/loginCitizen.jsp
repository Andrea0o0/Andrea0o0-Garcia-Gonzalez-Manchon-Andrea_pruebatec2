<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.practicalexercises.assessment2.logic.Citizen"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Login</title>

    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="css/sb-admin-2.min.css" rel="stylesheet">
    <link href="css/assessment2.css" rel="stylesheet">

</head>

<body class="bg-gradient-primary">

    <div class="container">

        <!-- Outer Row -->
        <div class="row justify-content-center">

            <div class="col-xl-10 col-lg-12 col-md-9">

                <div class="card o-hidden border-0 shadow-lg my-5">
                    <div class="card-body p-0">
                        <!-- Nested Row within Card Body -->
                        <div class="row">
                            <div class="col-lg-6 d-none d-lg-block bg-login-image">
                                <!--<img src="https://i.ibb.co/Vw639fm/Background.png" alt="alt"/>-->
                                <!--                                <a href="https://ibb.co/8YkXs1c"><img src="https://i.ibb.co/Vw639fm/Background.png" alt="Background" border="0"></a>-->
                            </div>
                            <div class="col-lg-6">
                                <div class="p-5">

                                    <%
                                        Map<String,Citizen> citizenFound = (HashMap<String, Citizen>) request.getSession().getAttribute("citizen");
                                        
                                        System.out.println(citizenFound != null && citizenFound.containsKey("citizen") && citizenFound.get("citizen") == null);
                                    %>

                                    <div class="form-container">
                                        <form id="myForm" class="user"  onsubmit="userAndPasswordValidateFormandSubmit(event)" action="SvCitizen" method="GET">
                                            <h2 class="center">Login citizen</h2>
                                            <div class="form-group">
                                                <input type="text" class="form-control form-control-user" id="username" name="username" placeholder="Username">
                                                <span class="error-message" id="usernameError">
                                                    Please enter a valid username has to be at least 8, also CAN include characters, letters, numbers, and punctuation marks
                                                </span>
                                            </div>

                                            <div class="form-group password-css">
                                                <div>
                                                    <input type="password" class="form-control form-control-user" id="inputPassword" name="inputPassword" placeholder="Password" autocomplete="new-password">
                                                    <span class="error-message" id="passwordError">
                                                        Please enter a valid password MUST CONTAIN at least 8 characters, uppercase, lowercase, number & sign
                                                    </span>
                                                </div>
                                                <div>
                                                    <span class="password-toggle" onclick="togglePasswordVisibility('passwordToggle','inputPassword')">
                                                        <i class="fas fa-eye-slash" id="passwordToggle"></i>
                                                    </span>
                                                </div>
                                            </div>
                                            <% if (citizenFound != null && citizenFound.containsKey("citizen") && citizenFound.get("citizen") == null) { %>
                                            <p class="error-message-login" >
                                                Please enter a valid username and a valid password
                                            </p>
                                            <%}%>
                                            
                                            <button type="submit" class="btn btn-primary btn-user btn-block">
                                                Login
                                            </button>
                                            <hr>
                                        </form>
                                        <div>
                                            <button type="button" class="btn btn-primary btn-user btn-block" onclick="location.href = 'signinCitizen.jsp';">
                                                Sign in Citizen
                                            </button>

                                            <button type="button" class="btn btn-primary btn-user btn-block" onclick="location.href = 'loginAdmin.jsp';">
                                                Login Admin
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </div>

    </div>

    <!--Script to display error messages (signin.jsp)-->
    <script src="js/registerValidation.js"></script>
    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin-2.min.js"></script>

</body>

</html>