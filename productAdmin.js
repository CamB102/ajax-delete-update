function generateProducts(){
        const xhttp = new XMLHttpRequest();
        xhttp.open("GET", "http://localhost:8080/be4-webservice/allProduct");
        xhttp.send();
        xhttp.onload = function() {
      const productArray = JSON.parse(this.responseText);
      const table = document.querySelector('.grid-container-table table');

      productArray.forEach(item => {
        const newRow = document.createElement("tr");
        newRow.className = "item";
        newRow.innerHTML = `
          <td class="grid-item">${item.id}</td>
          <td class="grid-item">${item.name}</td>
          <td class="grid-item">${item.categoryId}</td>
          <td class="grid-item">${item.title}</td>
          <td class="grid-item">${item.author}</td>
          <td class="grid-item">${item.stock}</td>
          <td class="grid-item">${item.price}</td>
          <td class="grid-item-action">
            <button class="grid-item-action-button" data-productid=${item.id} onclick="updateProduct(${item.id})">update</button>
            <button class="grid-item-action-button" data-productid=${item.id} onclick="deleteProduct(${item.id})">delete</button>
          </td>
        `;
        table.appendChild(newRow);
      });
    }  
    }

        window.onload = function () {
        generateProducts()
    } 

    
    function deleteProduct(productId) {
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET", "http://localhost:8080/be4-webservice/deleteProduct?id=" + productId);
    xhttp.send();

    $(document).ajaxSuccess(function() {
    // Fetch the updated data and refresh the table
    generateProducts();
});

    function updateProduct() {
    function updateProduct(productId) {
    // Get the updated product details from the form inputs
    const updatedProduct = {
      id: productId,
      name: document.getElementById("update-name").value,
      categoryId: document.getElementById("update-category").value,
      title: document.getElementById("update-title").value,
      author: document.getElementById("update-author").value,
      stock: document.getElementById("update-stock").value,
      price: document.getElementById("update-price").value,
    };

    const xhttp = new XMLHttpRequest();
    xhttp.open("POST", "http://localhost:8080/be4-webservice/updateProduct");
    xhttp.setRequestHeader("Content-Type", "application/json");
    xhttp.send(JSON.stringify(updatedProduct));

    xhttp.onload = function () {
      generateProducts(); // Update the table after successful update
    };

    };

    const xhttp = new XMLHttpRequest();
    xhttp.open("POST", "http://localhost:8080/be4-webservice/updateProduct");
    xhttp.setRequestHeader("Content-Type", "application/json");
    xhttp.send(JSON.stringify(updatedProduct));

    xhttp.onload = function() {
        // On successful update, fetch the updated data and refresh the table
        generateProducts();
    };
}
}