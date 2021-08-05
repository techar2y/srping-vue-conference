class ValidatePresentationFormUtil {
    validateTitle(context) {
        if (context.currentPresentation.title.length < 1) {
            context.validationTitleInfo.invalid = "Поле обязательное к заполнению";
            context.validationTitleInfo.value = false;
            return context.validationTitleInfo.value;
        } else if (context.currentPresentation.title.length > 31) {
            context.validationTitleInfo.invalid = "Заголовок не может быть больше 32 символов";
            context.validationTitleInfo.value = false;
            return context.validationTitleInfo.value;
        } else {
            context.validationTitleInfo.value = true;
            return context.validationTitleInfo.value;
        }
    } 
    
    validateRoom(context) {
        if (context.selectedRoomId == null) {
            context.validationRoomInfo.value = false;
            return context.validationRoomInfo.value;
        }

        let room = context.rooms.find(x => x.value === context.selectedRoomId);
        context.currentPresentation.room.id = room.value;
        context.currentPresentation.room.number = room.text.substring(room.text.indexOf(" ") + 1);
        context.validationRoomInfo.value = true;
        return context.validationRoomInfo.value;
    }
    
    validateLasts(context) {
        if (context.currentPresentation.lasts.length === 0) {
            context.validationLastsInfo.invalid = "Введите продолжительность доклада";
            context.validationLastsInfo.value = false;
            return context.validationLastsInfo.value;
        }
        context.validationLastsInfo.value = true;
        return context.validationLastsInfo.value;
    }
    
    validateSubject(context) {
        if (context.currentPresentation.subject.length < 1) {
            context.validationSubjectInfo.invalid = "Пожалуйста введите название предмета";
            context.validationSubjectInfo.value = false;
            return context.validationSubjectInfo.value;
        }

        context.validationSubjectInfo.value = true;
        return context.validationSubjectInfo.value;
    }
    
}

export default new ValidatePresentationFormUtil()