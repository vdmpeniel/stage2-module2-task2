# Servlets/Filter/Session

## Description
An application has a login page. The correct logins are "user" and "admin". The field "password" is not important, it can be any non-empty string. You need to handle login process. To achieve that create 2 servlets, a listener and a filter. All servlets, filters and listeners should be declared via annotations.

+ AuthFilter
+ ContextListener
+ LoginServlet
+ LogoutServlet

## Details

**AuthFilter** should:

+ be placed in `com.example.filter` package.
+ check session attribute "user" for paths `/user/*`. If there isn't, redirect the request to the `/login.jsp` page.

**ContextListener** should:

+ be placed in `com.example.listener` package.
+ set "servletTimeInit"  attribute in the context, "servletTimeInit" is a `LocalDateTime` object showing when the context was initialized.

**LoginServlet** should:
+ be placed in `com.example.servlet` package.
+ have url `/login`.
+ check session attribute "user".
+ for **GET** request if the session attribute "user" does not exist, redirect to the `/login.jsp` page, else redirect to the `/user/hello.jsp`.
+ for **POST** request check the request parameters. "login" should exist in [Users](src/main/java/com/example/Users.java) and the request parameter "password" shouldn't be empty. If parameters are correct set session attribute "user" and redirect to `/user/hello.jsp`, else forward to the `/login.jsp`.

**LogoutServlet** should:
+ be placed in `com.example.servlet` package.q
+ have url `/logout`.
+ delete session attribute "user".
+ invalidate session.
+ redirect to `/login.jsp`.