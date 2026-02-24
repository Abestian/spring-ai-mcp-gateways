FROM golang:1.24-alpine AS builder

RUN apk add --no-cache git

WORKDIR /build
RUN git clone https://github.com/github/github-mcp-server.git .
RUN go build -o /github-mcp-server ./cmd/github-mcp-server

FROM node:22-alpine

RUN npm install -g supergateway

COPY --from=builder /github-mcp-server /usr/local/bin/github-mcp-server
RUN chmod +x /usr/local/bin/github-mcp-server

EXPOSE 8001

ENTRYPOINT ["supergateway"]
