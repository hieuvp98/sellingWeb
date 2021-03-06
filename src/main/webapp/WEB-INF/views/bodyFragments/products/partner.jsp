
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script src="/resources/js/ajax/products/partner/ajax_partner.js"></script>
<h3 class="title-body" style="text-align: center">
    Quản Lý Đối Tác
</h3>
<div class="from-find float-right">
    <input type="text" name="search" id="input-search" placeholder="search..."
           class="input-search float-left">
    <div class="fas fa-search float-right" id="icon-search"></div>
</div>
<button type="button" class="btn btn-success float-left btn-search">
    <a href="${pageContext.request.contextPath}/admin/create-partner" style="text-decoration: none;color: white">
        <i class="fas fa-plus"></i>
        create
    </a>
</button>
<!-- TABLE -->
<div class="table-responsive" style="overflow-x:auto;overflow-y: auto">
    <table class="table text-center">
        <thead>
        <tr id="column-partner"  style="font-weight: 600"></tr>
        </thead>
        <tbody id="row-partner"></tbody>
    </table>
</div>
<!-- END_TABLE -->
<div class="pageable">
    <ul class="pagination"></ul>
</div>