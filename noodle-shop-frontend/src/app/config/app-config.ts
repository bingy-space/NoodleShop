export default{
    oidc:{
        clientId:'',
        issuer: '',
        redirectUri: 'http://localhost:4200/login/callback',
        scopes: ['openid','profile','email']
    }
}
// Scope provide access to info about a user
//  openid: required for authentication requests
//  profile: user's first name, last name, phone etc
//  email: user's email address