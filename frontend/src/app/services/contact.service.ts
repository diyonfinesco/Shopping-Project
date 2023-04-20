import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environment';
import { Contact } from '../models/contact';

@Injectable({
  providedIn: 'root'
})
export class ContactService {

  private apiURL = `${environment.apiUrl}/contacts`

  constructor(private http: HttpClient) { }

  createContact(contact: Contact) {
    return this.http.post<Contact>(this.apiURL, contact)
  }
}
