import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/common/product';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  // Set Property
  products: Product[] = [];

  // Inject ProductService
  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    this.listProducts();
  }

  listProducts(){
    // Method is invoked once you 'subscribe'
    this.productService.getProductList().subscribe(
      data => {
        // Assign results data to the Product array
        this.products = data;
        console.log(this.products)
      }
    )
  }
}
