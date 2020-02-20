# SimpleValidator
Validador baseado no vraptor para uso em validações fluentes.


## Uso

### Kotlin

#### Forma 1
``` 
  val validator = CustomValidator()

  validator
      .addIf(<Condição1>, SimpleMessage("INFO", "Condição %s não antedida", Severity.INFO, "a + b"))
      .addIf(<Condição2>, SimpleMessage.error("Condição2 %s não foi atendida, "b - a")
      .addIf(<Condição3>, SimpleMessage.warn("Condição3 %s não foi atendida, "b + b")
      .addIf(<Condição4>, SimpleMessage.info("Condição4 %s não foi atendida, "a + a")
      .ensure(<Condição5>, SimpleMessage.error("Condição5 %s não foi atendida, "a + a")
      .execWhen(severity = Severity.ERROR) { // Irá executar quando a severity for antedida
          if (it.isNotEmpty()) {
              // Se tiver a mensagem de erros, uma string com quebra por mensagem
              // uso padrão num alert.
          }
      }
      .execWhenNoMsgs{ // opcional
          // Executa quando não tiver mensagens
      }
```

#### Forma 2
``` 
  val validator = CustomValidator()

  validator
      .addIf(<Condição1>, SimpleMessage("INFO", "Condição %s não antedida", Severity.INFO, "a + b"))
      .addIf(<Condição2>, SimpleMessage.error("Condição2 %s não foi atendida, "b - a")
      .addIf(<Condição3>, SimpleMessage.warn("Condição3 %s não foi atendida, "b + b")
      .addIf(<Condição4>, SimpleMessage.info("Condição4 %s não foi atendida, "a + a")
      .ensure(<Condição5>, SimpleMessage.error("Condição5 %s não foi atendida, "a + a")
      .execWhen(severity = Severity.ERROR) { // Irá executar quando a severity for antedida
          if (it.isNotEmpty()) {
              // Se tiver a mensagem de erros, uma string com quebra por mensagem
              // uso padrão num alert.
          }
      }

  if(!validador.hasError()){
     // Bloco de codigo a ser executado
  }
```
### Java
  O Uso é muito semelhante ao anterior em kotlin.

```
  Validator validator = new CustomValidator();

  validator
      .addIf(<Condição1>, new SimpleMessage("INFO", "Condição %s não antedida", Severity.INFO, "a + b"))
      .addIf(<Condição2>, new SimpleMessage.error("Condição2 %s não foi atendida, "b - a")
      .addIf(<Condição3>, new SimpleMessage.warn("Condição3 %s não foi atendida, "b + b")
      .addIf(<Condição4>, new SimpleMessage.info("Condição4 %s não foi atendida, "a + a")
      .ensure(<Condição5>, new SimpleMessage.error("Condição5 %s não foi atendida, "a + a")
      .execWhen(Severity.ERROR) { // Irá executar quando a severity for antedida
          if (it.isNotEmpty()) {
              // Se tiver a mensagem de erros, uma string com quebra por mensagem
              // uso padrão num alert.
          }
      }

  if(!validador.hasError()){
     // Bloco de codigo a ser executado
  }
```
