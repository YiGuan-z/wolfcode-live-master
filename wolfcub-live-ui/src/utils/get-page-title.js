import defaultSettings from '@/settings'

const title = defaultSettings.title || '小狼直播'

export default function getPageTitle(pageTitle) {
  if (pageTitle) {
    return `${pageTitle} - ${title}`
  }
  return `${title}`
}
