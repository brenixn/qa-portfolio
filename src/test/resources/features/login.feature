Feature: Login no sistema

  Scenario: Login com credenciais validas
    Given que estou na página de login
    When faço login com usuário "tomsmith" e senha "SuperSecretPassword!"
    Then devo ver a mensagem "You logged into a secure area!"

  Scenario: Login com senha invalida
    Given que estou na página de login
    When faço login com usuário "tomsmith" e senha "senhaerrada"
    Then devo ver a mensagem "Your password is invalid!"

  Scenario: Login com usuario inexistente
    Given que estou na página de login
    When faço login com usuário "usuarioinexistente" e senha "qualquersenha"
    Then devo ver a mensagem "Your username is invalid!"