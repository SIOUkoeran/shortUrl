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
</div>
<div id="footer">
<div id="footer-text">
Last updated 2021-11-09 18:11:38 +0900
</div>
</div>

<script>
if (!hljs.initHighlighting.called) {
  hljs.initHighlighting.called = true
  ;[].slice.call(document.querySelectorAll('pre.highlight > code')).forEach(function (el) { hljs.highlightBlock(el) })
}
</script>
</body>
</html>