Feature: Compra Automatizada

  Background:
    Given Se carga la pantalla Login de SauceDemo

  @HappyPath @SauceDemo @01a
  Scenario Outline: Ejecutar la preuba en SAUCEDEMO
    When Se llena los campos del formulario login SauceDemo
      | Email   | Password   |
      | <Email> | <Password> |
    And Se presiona el boton login
    Then Se visualiza el dashboard de forma correcta
    When Se agregan dos productos al carrito al azar
    When El usuario va al carrito
    Then El usuario observa los productos en el carrito
    When se completa el formulario de compra
    When el usuario llena el formulario de checkout con nombre "<Nombre>", apellido "<Apellido>", y código postal "<CodigoPostal>"
    And el usuario hace clic en el botón de continuar
    And el usuario da clic en boton finish
    Then Se visualiza el texto de la forma correcta

    Examples:
      | Email         | Password     | Nombre | Apellido  | CodigoPostal |
      | standard_user | secret_sauce | Cheddy | Gavilanes |        060102 |
