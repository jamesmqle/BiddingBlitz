<h1>Catalog</h1>
<ul>
    <c:forEach var="item" items="${items}">
        <li>
            ${item.name} - ${item.startingBid}
            <a href="/bid?itemId=${item.id}">Place Bid</a>
        </li>
    </c:forEach>
</ul>
