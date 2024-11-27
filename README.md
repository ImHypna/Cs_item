CsItemCompose

Descrição do Projeto

O projeto CsItemCompose é um aplicativo Android desenvolvido com Jetpack Compose. Ele é estruturado para gerenciar itens utilizando um banco de dados local. Este projeto organiza o código em módulos distintos, incluindo camadas de banco de dados, modelo, ViewModel e interface do usuário.

----------------------------

Estrutura do Projeto

O projeto é dividido nas seguintes áreas principais:

Banco de Dados: Configuração e gerenciamento do armazenamento local.

Arquivos: appdatabase.kt, ItemDao.kt

Modelos: Representação das entidades e seus atributos.

Arquivo: item.kt

ViewModel: Gerenciamento da lógica de negócios e comunicação entre dados e interface.

Arquivos: viewmodel.kt, mainviewmodelfactory.kt

Interface do Usuário: Implementação da interface visual usando Jetpack Compose.

Arquivos: MainActivity.kt, Color.kt, Type.kt, Theme.kt

----------------------------

- - Divisão de Tarefas

- Pedro Henrique Cagol

Configurar o banco de dados:

appdatabase.kt

ItemDao.kt

Integrar o banco de dados com o ViewModel.

_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_

- Leonardo Kenji

Desenvolver a interface do usuário:

MainActivity.kt

Arquivos de tema: Color.kt, Type.kt, Theme.kt

Garantir que a interface seja responsiva e funcional.

_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_


- Kauê Galon

Implementar os modelos de dados:

item.kt

Desenvolver Interface do usuário:

MainActivity.ty

Colaborar com o ViewModel para conectar modelos ao banco de dados.

_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_

- Gabriel Ziliotto

Desenvolver e testar o ViewModel:

viewmodel.kt

mainviewmodelfactory.kt

----------------------------

Garantir que a lógica de negócios seja corretamente implementada.

Configuração do Ambiente

Instale o Android Studio.

Abra o projeto pelo arquivo build.gradle.kts na raiz.

Certifique-se de ter o SDK do Android configurado corretamente.

Execute o comando Gradle para sincronizar o projeto:

./gradlew build

----------------------------

Como Contribuir

Cada membro deve trabalhar em suas respectivas áreas, certificando-se de manter a organização do projeto. Antes de realizar commits, teste suas mudanças localmente para evitar erros.

Licença

Este projeto está licenciado sob a MIT License.

