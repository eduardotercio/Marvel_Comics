# Marvel Comics App

## Propósito

O **projeto** foi desenvolvido com o objetivo de criar uma experiência de busca e visualização de quadrinhos, integrando-se com a API oficial da Marvel. O aplicativo permite explorar os nomes e imagens dos quadrinhos e personagens, favoritar HQs para acesso offline, e navegar de forma rápida e eficiente.

## Funcionalidades

- **Busca de Quadrinhos**: O usuário vai ver quais são todos os quadrinhos disponíveis publicamente utilizando a API da Marvel.
- **Visualização de Personagens**: Para cada quadrinho, os personagens presentes são listados, com a funcionalidade de paginação.
- **Favoritar Quadrinhos**: O usuário pode favoritar qualquer quadrinho, permitindo que ele seja baixado localmente e acessado mesmo offline.
- **Listagem de Favoritos**: Todos os quadrinhos favoritados são exibidos em uma tela separada que também permite desfavoritar.

## Sobre a integração com Api

- **Hash MD5 para Requisições**: Para consumir a API da Marvel, é necessário gerar um hash MD5 usando uma combinação da chave API pública, privada e um timestamp, isso para qualquer requisição. 
- **Adaptação de URLs HTTP para HTTPS**: As URLs fornecidas pela API vinham em HTTP, o que causou um problema inicial com o carregamento de imagens pelo Coil, que requer HTTPS. Foi necessário adaptar as URLs para funcionar corretamente com HTTPS.
- **Limitação de Personagens**: Grande parte dos quadrinhos retornados pela API não contém personagens, o que afetou um terço do objetivo inicial do app. Além disso, dos quadrinhos que continham personagens, muitos tinham menos de 15, o que resultou na mudança da paginação para 8 personagens por vez.
- **API Marvel**: No mais, a integração com a API da Marvel é tranquila no geral.

## Tecnologias Utilizadas

O projeto foi desenvolvido utilizando as seguintes principais tecnologias:

- **Kotlin Coroutines**: Para gerenciar operações assíncronas de forma eficiente.
- **Koin**: Utilizado para injeção de dependência.
- **Ktor**: Para realizar requisições HTTP à API.
- **MongoDB**: Usado para persistência de dados locais dos quadrinhos favoritados.
- **Jetpack Compose**: Para a criação de uma interface de usuário moderna e reativa.
- **Coil**: Biblioteca utilizada para carregar imagens de maneira eficiente.
- **Arquitetura MVI**: Seguindo a arquitetura Model-View-Intent para garantir a manutenção e escalabilidade do código, com gerenciamento de estado bem definido.

## Como Rodar o Projeto

1. Clone o repositório do projeto.
2. Gerar chaves de api no site da Marvel: https://developer.marvel.com/account
3. Adicionar as chaves no arquivo keys.properties no projeto(criar se ele não existir)
4. Você pode gerar o APK de duas formas:
   - **Debug**: Basta rodar o projeto diretamente no modo `debug`.
   - **Release**: Ir até a aba de **Build** no Android Studio e crie um APK de release para o seu dispositivo.
