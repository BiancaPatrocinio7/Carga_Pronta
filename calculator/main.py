from kivy.app import App
from kivy.uix.screenmanager import ScreenManager, Screen
from kivy.uix.label import Label
from kivy.uix.button import Button
from kivy.uix.image import Image
from kivy.uix.boxlayout import BoxLayout
from kivy.uix.popup import Popup
from kivy.uix.textinput import TextInput
from kivy.graphics import Color, Rectangle

class MainScreen(Screen):
    def __init__(self, **kwargs):
        super(MainScreen, self).__init__(**kwargs)
        with self.canvas.before:
            Color(0, 0.3, 0.1, 1)  # Verde escuro
            self.rect = Rectangle(size=self.size, pos=self.pos)
        self.bind(size=self._update_rect, pos=self._update_rect)
        self.layout = BoxLayout(orientation='vertical', padding=50, spacing=30)
        self.add_widget(self.layout)

        self.layout.add_widget(Image(source='logo.png', size_hint=(None, None), size=(150, 150), pos_hint={'center_x': 0.5}))
        label_text = Label(text='Digite o nome do local de coleta', halign='center', font_size=24)
        self.layout.add_widget(label_text)
        self.nome_lista = TextInput(multiline=False)
        self.layout.add_widget(self.nome_lista)
        iniciar_btn = Button(text='Iniciar Lista')
        iniciar_btn.bind(on_press=self.iniciar_lista)
        self.layout.add_widget(iniciar_btn)

    def _update_rect(self, instance, value):
        self.rect.pos = instance.pos
        self.rect.size = instance.size

    def iniciar_lista(self, instance):
        if self.nome_lista.text:
            app.root.current = 'equipamentos'
        else:
            popup = Popup(title='Erro', content=Label(text='Por favor, insira um nome para a lista.'), size_hint=(None, None), size=(300, 200))
            popup.open()

class EquipamentosScreen(Screen):
    def __init__(self, **kwargs):
        super(EquipamentosScreen, self).__init__(**kwargs)
        self.add_widget(Label(text='Equipamentos Screen'))

class MyApp(App):
    def build(self):
        self.title = 'Calculadora de Equipamentos'
        self.manager = ScreenManager()
        self.manager.add_widget(MainScreen(name='main'))
        self.manager.add_widget(EquipamentosScreen(name='equipamentos'))
        return self.manager

if __name__ == '__main__':
    app = MyApp()
    app.run()
