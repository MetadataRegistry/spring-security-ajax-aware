Spring Security AJAX Aware
------------------------------------

This project provides implementation of [AuthenticationEntryPoint](http://docs.spring.io/spring-security/site/docs/3.0.x/apidocs/org/springframework/security/web/AuthenticationEntryPoint.html)
which returns status 401 FORBIDDEN for AJAX calls requesting `application/json` format in their `Accept` headers on the first place.

The primary use case is to use [AngularJS](https://angularjs.org) and its [Angular HTTP Auth](https://github.com/witoldsz/angular-http-auth) module
with [Grails](http://grails.org) framework and its [Spring Security Core Plugin](http://grails.org/plugin/spring-security-core). 


## Usage
You need to override `authenticationEntryPoint` bean in your application.

For the most simple use case in Grails you can easy set it up using following snippet in `/grails-app/conf/spring/resources.groovy`:

```
import org.modelcatalogue.core.security.ajax.AjaxAwareLoginUrlAuthenticationEntryPoint

// Place your Spring DSL code here
beans = {
    authenticationEntryPoint(AjaxAwareLoginUrlAuthenticationEntryPoint) {
        loginFormUrl    = '/login/auth' // has to be specified even though it's ignored
        portMapper      = ref('portMapper')
        portResolver    = ref('portResolver')
    }
}
```


## Kudos
Inspired by http://stackoverflow.com/questions/8171256/use-spring-security-to-tell-ajax-requests-where-the-login-page-is