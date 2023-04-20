import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ContactService } from 'src/app/services/contact.service';

@Component({
  selector: 'app-t-contact',
  templateUrl: './t-contact.component.html',
  styleUrls: []
})
export class TContactComponent {
  errorMessage = ""

  constructor(private fb: FormBuilder, private contactService: ContactService, private router: Router) { }

  contactForm = this.fb.group({
    name: ["", [Validators.required]],
    email: ["", [Validators.email, Validators.required]],
    subject: ["", Validators.required],
    message: ["", Validators.required],
  })

  async onSubmit() {
    const { name, email, subject, message } = this.contactForm.value

    if (name && email && subject && message) {
      this.contactService.createContact({ name, email, subject, message }).subscribe({
        next: (data) => {
          this.router.navigateByUrl('/')
        },
        error: (error) => {
          this.errorMessage = error.error.message
        }
      })
    }
  }
}
