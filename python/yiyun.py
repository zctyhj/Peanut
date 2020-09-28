# coding=utf-8
import wx
import time

'''
main program
author : hunter huang
date   : 20200926

update : add event for TextCtrl when text change. it will run time to update the status while we edit the text or coding.
date   : 20200928


# https://pypi.doubanio.com/simple/
# wxPython
'''


class FirseFrame(wx.Frame):
    filename = None
    contents = None

    def __init__(self, parent=None, id=1, title='', pos=wx.DefaultPosition, size=wx.DefaultSize,
                 style=wx.DEFAULT_FRAME_STYLE):
        wx.Frame.__init__(self, parent, id, title, pos, size, style)
        self.filename = wx.TextCtrl(self)
        self.contents = wx.TextCtrl(self, style=wx.TE_MULTILINE | wx.HSCROLL)
        self.InitUI()
        self.contents.Bind(wx.EVT_KEY_DOWN, self.OnKeyDown)
        # self.contents.Bind(wx.EVT_LEFT_DOWN, self.on_left_down)
        # self.contents.Bind(wx.EVT_LEFT_UP, self.on_left_up)
        # self.contents.Bind(wx.EVT_MOTION, self.on_mouse_move)
        self.contents.Bind(wx.EVT_TEXT, self.textChage)
        pass

    def textChage(self, event):
        # print(event)
        print(self.contents.GetValue())

    def OnKeyDown(self, event):
        keycode = event.GetKeyCode()
        if keycode == wx.WXK_F1:
            self.SetPosition((0, 0))
            self.SetSize(wx.DisplaySize())
        else:
            event.Skip()

    def on_left_down(self, event):
        print(self.contents.GetNumberOfLines())
        print('鼠标按下')

    def on_left_up(self, event):
        print(self.contents.GetNumberOfLines())
        print('鼠标释放')

    def on_mouse_move(self, event):
        # print('鼠标移动')
        if event.Dragging() and event.LeftIsDown():
            # 鼠标正在移动，按左键移动
            # pos = event.GetPosition(self)  # 取出位置
            # print(pos)
            pass

    def InitUI(self):
        # menubar setting
        filemenu = wx.Menu()
        editmenu = wx.Menu()
        viewmenu = wx.Menu()
        langmenu = wx.Menu()
        settmenu = wx.Menu()
        toolmenu = wx.Menu()
        plugmenu = wx.Menu()
        windmenu = wx.Menu()
        helpmenu = wx.Menu()

        menubar = wx.MenuBar()
        menubar.Append(filemenu, "&File")
        menubar.Append(editmenu, "&Edit")
        menubar.Append(viewmenu, "&View")
        menubar.Append(langmenu, "&Language")
        menubar.Append(settmenu, "&Setting")
        menubar.Append(toolmenu, "&Tools")
        menubar.Append(plugmenu, "&Plugins")
        menubar.Append(windmenu, "&Window")
        menubar.Append(helpmenu, "&Help")

        quitmenuitem = wx.MenuItem(filemenu, wx.ID_EXIT, "&Quit\tCtrl+Q")
        newmenuitem = wx.MenuItem(filemenu, wx.ID_NEW, "&New", "huang")
        openmenitem = wx.MenuItem(filemenu, wx.ID_OPEN, "&Open")
        savemenuitem = wx.MenuItem(filemenu, wx.ID_SAVE, "&Save")
        quitmenuitem.SetBitmap(wx.Bitmap("new_wiz.png"))

        filemenu.Append(wx.ID_NEW, '&New')
        filemenu.Append(wx.ID_OPEN, '&Open')
        filemenu.Append(wx.ID_SAVE, '&Save')
        filemenu.Append(wx.ID_EXIT, "&Quit", "Quit Applications")
        filemenu.Append(newmenuitem)
        filemenu.Append(openmenitem)
        filemenu.Append(savemenuitem)
        filemenu.Append(quitmenuitem)

        self.SetMenuBar(menubar)
        self.filename.SetValue('test.txt')
        loadbutton = wx.Button(self, label='Open')
        savebutton = wx.Button(self, label='Save')
        loadbutton.Bind(wx.EVT_BUTTON, self.load)
        savebutton.Bind(wx.EVT_BUTTON, self.save)

        self.CreateStatusBar()
        # statusBar

        # self.SetBackgroundColour('#352b2b')
        self.contents.SetValue(
            "hello, I am Hunter huang."
            "\nthis is a python editor sample using wxPython plugins.\n"
            "to install wxPython,you can input \n\t'sudo apt-get install python-wxtools'\nunder ubuntu")
        self.contents.SetForegroundColour('#352b2b')

        hbox = wx.BoxSizer()
        hbox.Add(self.filename, proportion=1, flag=wx.EXPAND)
        hbox.Add(loadbutton, proportion=0, flag=wx.LEFT, border=5)
        hbox.Add(savebutton, proportion=0, flag=wx.LEFT, border=5)

        vbox = wx.BoxSizer(wx.VERTICAL)
        vbox.Add(hbox, proportion=0, flag=wx.EXPAND | wx.ALL, border=5)
        vbox.Add(self.contents, proportion=1,
                 flag=wx.EXPAND | wx.LEFT | wx.BOTTOM | wx.RIGHT, border=5)
        self.SetSizer(vbox)
        pass

    def load(self, event):
        file = open(self.filename.GetValue())
        # 格式化成2016-03-20 11:45:39形式
        print(time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()) + " load function .")
        self.contents.SetValue(file.read())
        print(self.contents.GetLineLength(1))
        print(self.contents.GetNumberOfLines())
        file.close()

    def save(self, event):
        file = open(self.filename.GetValue(), 'w')
        # 格式化成2016-03-20 11:45:39形式
        print(time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()) + " save function .")
        file.write(self.contents.GetValue())
        print(self.contents.GetLineLength(1))
        print(self.contents.GetNumberOfLines())
        file.close()


class MainApp(wx.App):
    def OnInit(self):
        width = int(wx.DisplaySize()[0] / 2)
        height = int(wx.DisplaySize()[1] / 2)
        # setting width height is default value and on the center of screen
        frame = FirseFrame(id=1, title="Simple Editor", size=(width, height))
        frame.Show()
        return True


def main():
    app = MainApp()
    app.MainLoop()


if __name__ == "__main__":
    main()
