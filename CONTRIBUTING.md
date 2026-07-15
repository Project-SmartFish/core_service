# Guia de Contribuição - SmartFish

Este documento define o fluxo de contribuição, os padrões de código e os critérios mínimos de qualidade para o desenvolvimento do app SmartFish.

As regras se aplicam a todos os integrantes, independentemente do serviço ou funcionalidade em que estejam trabalhando.

O sistema é composto principalmente por:

- `core-service`: Java, Spring Boot, PostgreSQL, Spring Security, Kafka e integração REST;
- `ml-service`: Python, FastAPI, consumo Kafka, treinamento e disponibilização do modelo de favorabilidade;
- infraestrutura local: PostgreSQL, Kafka e demais serviços definidos no `docker-compose.yml`.

Em caso de dúvida arquitetural ou de responsabilidade, alinhe com o responsável técnico da área antes de implementar.

---

## 2. Regras gerais

- Nunca faça commit diretamente nas branches `main` ou `develop`;
- Todo código deve entrar por Pull Request;
- Todo PR precisa de pelo menos uma aprovação antes do merge;
- Não faça merge com testes falhando;
- Não versione credenciais, tokens, chaves privadas, arquivos `.env` ou dados pessoais;
- Não envie arquivos gerados pela IDE ou pelo processo de build;
- Não misture mudanças sem relação no mesmo PR;
- Antes de solicitar revisão, revise seu próprio diff.

---

## 3. Estrutura das branches

### Fluxo padrão

```text
develop
   ↓
feature/<issue>-<descricao>
   ↓
Pull Request
   ↓
develop
   ↓
release/<versao-ou-sprint>
   ↓
main
```

### Hotfix

```text
main
  ↓
hotfix/<descricao>
  ↓
Pull Request para main
  ↓
merge da correção também em develop
```

---

## 4. Nomenclatura de branches

Use letras minúsculas, números e hífens.

### Formato

```text
tipo/#codigo-descricao-curta
```

### Exemplos

```text
feature/#12-criar-local-pesca
feature/#18-criar-pescaria-grupo
feature/#24-publicar-evento-pescaria-registrada
feature/#31-consultar-favorabilidade
fix/#27-corrigir-calculo-taxa-captura
hotfix/corrigir-expiracao-jwt
docs/atualizar-diagrama-arquitetura
chore/configurar-testcontainers
release/sprint-02
```

### Tipos permitidos

| Prefixo | Quando utilizar |
| --- | --- |
| `feature/` | Nova funcionalidade |
| `fix/` | Correção de bug que ainda não está em produção |
| `hotfix/` | Correção urgente sobre a versão estável |
| `release/` | Preparação de entrega |
| `docs/` | Alteração apenas em documentação |
| `chore/` | Infraestrutura, dependências, configuração ou manutenção |

---

## 5. Padrão de commits

O projeto segue **Conventional Commits**.

### Formato

```text
tipo(escopo): descrição curta
```

O escopo é recomendado quando ajuda a identificar a área alterada.

### Tipos permitidos

| Tipo | Uso |
| --- | --- |
| `feat` | Nova funcionalidade |
| `fix` | Correção de bug |
| `docs` | Documentação |
| `refactor` | Refatoração sem mudança intencional de comportamento |
| `style` | Formatação sem alteração de lógica |
| `test` | Adição ou correção de testes |
| `chore` | Configuração, dependências e manutenção |
| `build` | Alterações no build ou empacotamento |
| `ci` | Pipeline de integração e entrega |
| `perf` | Melhoria de desempenho |
| `revert` | Reversão de commit anterior |

### Escopos sugeridos

```text
auth
user
fishing-spot
fishing-event
fishing-record
capture
prediction
kafka
database
security
logging
infra
docker
ci
docs
```

### Exemplos corretos

```text
feat(fishing-spot): adiciona cadastro de local de pesca
feat(fishing-event): permite adicionar participante à pescaria
feat(kafka): publica evento de pescaria registrada
fix(fishing-record): corrige cálculo de peixes por hora
refactor(security): extrai geração de token para serviço dedicado
test(fishing-record): adiciona testes do cálculo de resultado geral
docs(architecture): atualiza diagrama de pacotes
chore(database): adiciona migração inicial do Flyway
ci: executa testes Maven em cada pull request
```

### Regras para a descrição

- Use verbo no presente;
- Seja objetivo;
- Não termine com ponto;
- Explique o que o commit faz;
- Evite colocar várias responsabilidades no mesmo commit.

---

## 7. Fluxo de trabalho com Git

### Criar uma nova branch

```bash
git checkout develop
git pull origin develop
git checkout -b feature/12-criar-local-pesca
```

### Atualizar a branch antes de abrir o PR

```bash
git checkout develop
git pull origin develop
git checkout feature/12-criar-local-pesca
git rebase develop
```

Resolva eventuais conflitos e execute os testes.

### Enviar a branch

Primeiro envio:

```bash
git push -u origin feature/#12-criar-local-pesca
```

Após um rebase em uma branch já publicada:

```bash
git push --force-with-lease origin feature/#12-criar-local-pesca
```

### Atenção

- Faça rebase apenas em sua própria branch;
- Não reescreva o histórico de uma branch compartilhada sem alinhamento;
- Nunca use `git push --force` simples;
- Prefira `--force-with-lease`, pois ele evita sobrescrever alterações remotas inesperadas.

---

Ao contribuir, priorize código simples, legível, testável e coerente com a arquitetura acordada pela equipe.

---

## Obervação

###### Este documento está sujeito a futuras alterações visando a melhor produtividade e organização do projeto.

---