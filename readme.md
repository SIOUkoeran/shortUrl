DB 
---
</br>
<img width = "40%" src ="https://user-images.githubusercontent.com/35398494/141236043-dc8c7a7c-0534-4bc7-b8a1-704e90e78e8c.png"/>

<body class="book toc2 toc-left">
<div id="header">
<h1>API Guide</h1>
<div id="toc" class="toc2">
<div id="toctitle">Table of Contents</div>
<ul class="sectlevel1">
<li><a href="#api">1. api</a></li>
<li><a href="#_주소_생성">2. 주소 생성</a>
<ul class="sectlevel2">
<li><a href="#_주소_생성_request_fields">2.1. Request fields</a></li>
<li><a href="#_주소_생성_curl_request">2.2. Curl request</a></li>
<li><a href="#_주소_생성_http_request">2.3. HTTP request</a></li>
<li><a href="#_주소_생성_request_headers">2.4. Request headers</a></li>
<li><a href="#_주소_생성_http_response">2.5. HTTP response</a></li>
<li><a href="#_주소_생성_response_fields">2.6. Response fields</a></li>
<li><a href="#_주소_생성_links">2.7. Links</a></li>
</ul>
</li>
<li><a href="#_주소_이동">3. 주소 이동</a></li>
</ul>
</div>
</div>
<div id="content">
<div class="sect1">
<h2 id="api"><a class="anchor" href="#api"></a><a class="link" href="#api">1. api</a></h2>
<div class="sectionbody">

</div>
</div>
<div class="sect1">
<h2 id="_주소_생성"><a class="anchor" href="#_주소_생성"></a><a class="link" href="#_주소_생성">2. 주소 생성</a></h2>
<div class="sectionbody">
<div class="paragraph">
<p><code>Post</code> 요청을 사용해서 새로운 URL을 만들수 있습니다.</p>
</div>
<div class="sect2">
<h3 id="_주소_생성_request_fields"><a class="anchor" href="#_주소_생성_request_fields"></a><a class="link" href="#_주소_생성_request_fields">2.1. Request fields</a></h3>
<table class="tableblock frame-all grid-all stretch">
<colgroup>
<col style="width: 33.3333%;">
<col style="width: 33.3333%;">
<col style="width: 33.3334%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Path</th>
<th class="tableblock halign-left valign-top">Type</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>url</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">url to change</p></td>
</tr>
</tbody>
</table>
</div>
<div class="sect2">
<h3 id="_주소_생성_curl_request"><a class="anchor" href="#_주소_생성_curl_request"></a><a class="link" href="#_주소_생성_curl_request">2.2. Curl request</a></h3>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight"><code class="language-bash hljs" data-lang="bash">$ curl 'http://localhost:8080/bit.ly/' -i -X POST \
    -H 'Content-Type: application/json;charset=UTF-8' \
    -H 'Accept: application/hal+json' \
    -d '{
  "url" : "https://www.naver.com"
}'</code></pre>
</div>
</div>
</div>
<div class="sect2">
<h3 id="_주소_생성_http_request"><a class="anchor" href="#_주소_생성_http_request"></a><a class="link" href="#_주소_생성_http_request">2.3. HTTP request</a></h3>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight nowrap"><code class="language-http hljs" data-lang="http">POST /bit.ly/ HTTP/1.1
Content-Type: application/json;charset=UTF-8
Accept: application/hal+json
Content-Length: 39
Host: localhost:8080

{
  "url" : "https://www.naver.com"
}</code></pre>
</div>
</div>
</div>
<div class="sect2">
<h3 id="_주소_생성_request_headers"><a class="anchor" href="#_주소_생성_request_headers"></a><a class="link" href="#_주소_생성_request_headers">2.4. Request headers</a></h3>
<table class="tableblock frame-all grid-all stretch">
<colgroup>
<col style="width: 50%;">
<col style="width: 50%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Name</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Content-Type</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">content type header</p></td>
</tr>
</tbody>
</table>
</div>
<div class="sect2">
<h3 id="_주소_생성_http_response"><a class="anchor" href="#_주소_생성_http_response"></a><a class="link" href="#_주소_생성_http_response">2.5. HTTP response</a></h3>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight nowrap"><code class="language-http hljs" data-lang="http">HTTP/1.1 201 Created
Location: http://localhost:8080/bit.ly/1
Content-Type: application/hal+json
Content-Length: 247

{
  "shortUrl" : "9e4cfae4",
  "url" : "https://www.naver.com",
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/bit.ly"
    },
    "response-url" : {
      "href" : "http://localhost:8080/bit.ly/9e4cfae4"
    }
  }
}</code></pre>
</div>
</div>
</div>
<div class="sect2">
<h3 id="_주소_생성_response_fields"><a class="anchor" href="#_주소_생성_response_fields"></a><a class="link" href="#_주소_생성_response_fields">2.6. Response fields</a></h3>
<table class="tableblock frame-all grid-all stretch">
<colgroup>
<col style="width: 33.3333%;">
<col style="width: 33.3333%;">
<col style="width: 33.3334%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Path</th>
<th class="tableblock halign-left valign-top">Type</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>url</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">url to change</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>shortUrl</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">shortened url</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_links.self.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">link to self</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_links.response-url.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">response-link</p></td>
</tr>
</tbody>
</table>
</div>
<div class="sect2">
<h3 id="_주소_생성_links"><a class="anchor" href="#_주소_생성_links"></a><a class="link" href="#_주소_생성_links">2.7. Links</a></h3>
<table class="tableblock frame-all grid-all stretch">
<colgroup>
<col style="width: 50%;">
<col style="width: 50%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Relation</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>self</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">self link</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>response-url</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">redirect original url</p></td>
</tr>
</tbody>
</table>
</div>
</div>
</div>
<div class="sect1">
<h2 id="_주소_이동"><a class="anchor" href="#_주소_이동"></a><a class="link" href="#_주소_이동">3. 주소 이동</a></h2>
<div class="sectionbody">
<div class="paragraph">
<p><code>Get</code> 요청을 사용해서 기존 URL로 생성된 새로운 URL을 통해 사이트에 접속할 수 있습니다.</p>
</div>
</div>
</div>
<div class="sect1">
<h2 id="_오류"><a class="anchor" href="#_오류"></a><a class="link" href="#_오류">4. 오류</a></h2>
<div class="sectionbody">
<div class="sect2">
<h3 id="_잘못된_url형식_요청"><a class="anchor" href="#_잘못된_url형식_요청"></a><a class="link" href="#_잘못된_url형식_요청">4.1. 잘못된 URL형식 요청</a></h3>
<div class="sect3">
<h4 id="_잘못된_url형식_요청_http_request"><a class="anchor" href="#_잘못된_url형식_요청_http_request"></a><a class="link" href="#_잘못된_url형식_요청_http_request">4.1.1. HTTP request</a></h4>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight nowrap"><code class="language-http hljs" data-lang="http">POST /bit.ly HTTP/1.1
Content-Type: application/json;charset=UTF-8
Accept: application/hal+json
Content-Length: 31
Host: localhost:8080

{
  "url" : "www.naver.com"
}</code></pre>
</div>
</div>
</div>
<div class="sect3">
<h4 id="_잘못된_url형식_요청_response_fields"><a class="anchor" href="#_잘못된_url형식_요청_response_fields"></a><a class="link" href="#_잘못된_url형식_요청_response_fields">4.1.2. Response fields</a></h4>
<table class="tableblock frame-all grid-all stretch">
<colgroup>
<col style="width: 33.3333%;">
<col style="width: 33.3333%;">
<col style="width: 33.3334%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Path</th>
<th class="tableblock halign-left valign-top">Type</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>code</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">error code</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>message</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">error message</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_links.request-url.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">return request-url</p></td>
</tr>
</tbody>
</table>
</div>
<div class="sect3">
<h4 id="_잘못된_url형식_요청_http_response"><a class="anchor" href="#_잘못된_url형식_요청_http_response"></a><a class="link" href="#_잘못된_url형식_요청_http_response">4.1.3. HTTP response</a></h4>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight nowrap"><code class="language-http hljs" data-lang="http">HTTP/1.1 400 Bad Request
Content-Type: application/hal+json
Content-Length: 182

{
  "code" : "BAD_URL",
  "message" : "URL형식을 다시 확인해주세요",
  "_links" : {
    "request-url" : {
      "href" : "http://localhost:8080/bit.ly"
    }
  }
}</code></pre>
</div>
</div>
</div>
<div class="sect3">
<h4 id="_잘못된_url형식_요청_links"><a class="anchor" href="#_잘못된_url형식_요청_links"></a><a class="link" href="#_잘못된_url형식_요청_links">4.1.4. Links</a></h4>
<table class="tableblock frame-all grid-all stretch">
<colgroup>
<col style="width: 50%;">
<col style="width: 50%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Relation</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>request-url</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">request-url</p></td>
</tr>
</tbody>
</table>
</div>
</div>
<div class="sect2">
<h3 id="_존재하지않는_url_요청"><a class="anchor" href="#_존재하지않는_url_요청"></a><a class="link" href="#_존재하지않는_url_요청">4.2. 존재하지않는 URL 요청</a></h3>
<div class="sect3">
<h4 id="_존재하지않는_url_요청_http_request"><a class="anchor" href="#_존재하지않는_url_요청_http_request"></a><a class="link" href="#_존재하지않는_url_요청_http_request">4.2.1. HTTP request</a></h4>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight nowrap"><code class="language-http hljs" data-lang="http">GET /bit.ly/notFound HTTP/1.1
Host: localhost:8080</code></pre>
</div>
</div>
</div>
<div class="sect3">
<h4 id="_존재하지않는_url_요청_response_fields"><a class="anchor" href="#_존재하지않는_url_요청_response_fields"></a><a class="link" href="#_존재하지않는_url_요청_response_fields">4.2.2. Response fields</a></h4>
<table class="tableblock frame-all grid-all stretch">
<colgroup>
<col style="width: 33.3333%;">
<col style="width: 33.3333%;">
<col style="width: 33.3334%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Path</th>
<th class="tableblock halign-left valign-top">Type</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>code</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">error code</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>message</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">error message</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>_links.request-url.href</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">return request url</p></td>
</tr>
</tbody>
</table>
</div>
<div class="sect3">
<h4 id="_존재하지않는_url_요청_http_response"><a class="anchor" href="#_존재하지않는_url_요청_http_response"></a><a class="link" href="#_존재하지않는_url_요청_http_response">4.2.3. HTTP response</a></h4>
<div class="listingblock">
<div class="content">
<pre class="highlightjs highlight nowrap"><code class="language-http hljs" data-lang="http">HTTP/1.1 404 Not Found
Content-Type: application/hal+json
Content-Length: 180

{
  "code" : "URL_NOT_FOUND",
  "message" : "URL을 찾지 못했습니다.",
  "_links" : {
    "request-url" : {
      "href" : "http://localhost:8080/bit.ly"
    }
  }
}</code></pre>
</div>
</div>
</div>
<div class="sect3">
<h4 id="_존재하지않는_url_요청_links"><a class="anchor" href="#_존재하지않는_url_요청_links"></a><a class="link" href="#_존재하지않는_url_요청_links">4.2.4. Links</a></h4>
<table class="tableblock frame-all grid-all stretch">
<colgroup>
<col style="width: 50%;">
<col style="width: 50%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Relation</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>request-url</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">request-url</p></td>
</tr>
</tbody>
</table>
</div>
</div>
</div>
</div>
</div>
<div id="footer">
<div id="footer-text">
Last updated 2021-11-09 21:04:57 +0900
</div>
</div>

</body>
</html>